use std::fs::File;
use std::io::prelude::*;


const TAU_N: f64 = 881.5;
const MEV_PER_K: f64 = 8.617330e-11;

enum State {
    Equilibrium,
    NeutronDecay{decouple_ratio: f64, decouple_time: f64},
    FreezeOut{freeze_out_ratio: f64},
}

fn time_to_temperature(time: f64) -> f64 {
    1.3e10 / time.sqrt() * MEV_PER_K
}

fn compute_pn_ratio(neutron_decouple_temperature: f64, freeze_out_temperature: f64, filename: &str) {

    let n_steps = 1000;
    let start_time_log: f64 = -2.;
    let end_time_log: f64 = 5.;
    let factor = (end_time_log - start_time_log) / n_steps as f64;

    let mut state = State::Equilibrium;

    let mut file = File::create(filename).expect("Error: couldn't open file");

    for i in 0..(n_steps+1) {

        let time = (10_f64).powf((i as f64)*factor + start_time_log);
        let temperature = time_to_temperature(time);

        let pn_ratio: f64;

        match state{
            State::Equilibrium => {
                pn_ratio = (-1.29 / temperature).exp();
                if temperature <= neutron_decouple_temperature {
                    state = State::NeutronDecay{decouple_ratio: pn_ratio, decouple_time: time};
                }
            },
            State::NeutronDecay{decouple_ratio, decouple_time} => {
                let t = time - decouple_time;
                pn_ratio = decouple_ratio * (-t / TAU_N).exp() / (1. + decouple_ratio * (1. - (-t / TAU_N).exp()));

                if temperature <= freeze_out_temperature {
                    state = State::FreezeOut{freeze_out_ratio: pn_ratio};
                }
            },
            State::FreezeOut{freeze_out_ratio} => {
                pn_ratio = freeze_out_ratio;
            }
        }

        write!(&mut file, "{}, {}, {}\n", time, temperature, pn_ratio).expect("Error: couldn't write to file");
    }
}


fn main() {
    let neutron_decouple_temperature: f64 = 1.;
    let freeze_out_temperature: f64 = 0.07;

    compute_pn_ratio(neutron_decouple_temperature, freeze_out_temperature, "pn-ratio.csv");
    compute_pn_ratio(-1., -1., "pn-ratio-nodecouple.csv");
    compute_pn_ratio(neutron_decouple_temperature, -1., "pn-ratio-nofreeze.csv");
}
