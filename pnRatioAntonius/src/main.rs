use std::fs::File;
use std::io::prelude::*;


const tauN: f64 = 881.5;
const MeVperK: f64 = 8.617330e-11;

enum State {
    Equilibrium,
    NeutronDecay{decoupleRatio: f64, decoupleTime: f64},
    FreezeOut{freezeOutRatio: f64},
}

fn timeToTemperature(time: f64) -> f64 {
    1.3e10 / time.sqrt() * MeVperK
}

fn computePNRatio(neutronDecoupleTemperature: f64, freezeOutTemperature: f64, filename: &str) {

    let nSteps = 1000;
    let startTimeLog: f64 = -2.;
    let endTimeLog: f64 = 5.;
    let factor = (endTimeLog - startTimeLog) / nSteps as f64;

    let mut state = State::Equilibrium;

    let mut file = File::create(filename).expect("Error: couldn't open file");

    for i in 0..(nSteps+1) {

        let time = (10_f64).powf((i as f64)*factor + startTimeLog);
        let temperature = timeToTemperature(time);

        let pnRatio: f64;

        match state{
            State::Equilibrium => {
                pnRatio = (-1.29 / temperature).exp();
                if temperature <= neutronDecoupleTemperature {
                    state = State::NeutronDecay{decoupleRatio: pnRatio, decoupleTime: time};
                }
            },
            State::NeutronDecay{decoupleRatio, decoupleTime} => {
                let t = time - decoupleTime;
                pnRatio = decoupleRatio * (-t / tauN).exp() / (1. + decoupleRatio * (1. - (-t / tauN).exp()));

                if temperature <= freezeOutTemperature {
                    state = State::FreezeOut{freezeOutRatio: pnRatio};
                }
            },
            State::FreezeOut{freezeOutRatio} => {
                pnRatio = freezeOutRatio;
            }
        }

        write!(&mut file, "{}, {}, {}\n", time, temperature, pnRatio).expect("Error: couldn't write to file");
    }
}


fn main() {
    let neutronDecoupleTemperature: f64 = 1.;
    let freezeOutTemperature: f64 = 0.07;

    computePNRatio(neutronDecoupleTemperature, freezeOutTemperature, "pn-ratio.csv");
    computePNRatio(-1., -1., "pn-ratio-nodecouple.csv");
    computePNRatio(neutronDecoupleTemperature, -1., "pn-ratio-nofreeze.csv");
}
