use std::fs;

const MAX_CLOCK_A: i32 = 220;
const MAX_CLOCK_B: i32 = 240;
const PERIOD_B: i32 = 40;

pub fn a(input_file: &str) -> i32{
    let input = fs::read_to_string(input_file).expect("Unable to read file.");

    let mut instructions = input
        .split_terminator('\n')
        .map(|x| {
            if x.eq("noop") {
                (0i32, 0i32)
            } else {
                let instr = x.split(' ');

                (1i32, instr.last().unwrap().parse::<i32>().unwrap())
            }
        });

    let mut strength = 0;

    let mut clock = 1;
    let mut progress = 0;
    let mut curr_instr = (0i32, 0i32);      // noop is code 0, addx is code 1
    let mut x = 1;
    while clock <= MAX_CLOCK_A {
        if progress == 0 {
            x += curr_instr.1;
            curr_instr = instructions.next().unwrap();

            match curr_instr.0 {
                0 => progress = 1,
                1 => progress = 2,
                _ => unreachable!(),
            }
        }

        if (clock - 20) % 40 == 0 {
            strength += clock * x;
        }

        progress -= 1;
        clock += 1;
    }

    strength
}

pub fn b(input_file: &str) -> String {
    let input = fs::read_to_string(input_file).expect("Unable to read file.");

    let mut instructions = input
        .split_terminator('\n')
        .map(|x| {
            if x.eq("noop") {
                (0i32, 0i32)
            } else {
                let instr = x.split(' ');

                (1i32, instr.last().unwrap().parse::<i32>().unwrap())
            }
        });

    let mut screen = String::from("");

    let mut clock = 1;
    let mut progress = 0;
    let mut curr_instr = (0i32, 0i32);      // noop is code 0, addx is code 1
    let mut x = 1;
    while clock <= MAX_CLOCK_B {
        let pixel_pos = (clock - 1) % PERIOD_B;

        if progress == 0 {
            x += curr_instr.1;
            curr_instr = instructions.next().unwrap();

            match curr_instr.0 {
                0 => progress = 1,
                1 => progress = 2,
                _ => unreachable!(),
            }
        }

        // checking if the pixel falls within the sprite
        if (x - 1 <= pixel_pos) && (pixel_pos <= x + 1) {
            screen.push('@');
        } else {
            screen.push('.');
        }

        // adding new line when we hit the end of the screen
        if clock % PERIOD_B == 0 {
            screen.push('\n');
        }

        progress -= 1;
        clock += 1;
    }

    screen
}
