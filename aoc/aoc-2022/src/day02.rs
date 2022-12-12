use::core::str::Chars;
use std::fs;

pub fn a (input_file: &str) -> i64 {
    let input = fs::read_to_string(input_file).expect("Unable to read file.");

    let mut input = input;
    // using pop to get rid of trailing newline
    input.pop();

    // points handed out for draw/loss/win respectively
    let outcome_points: [i64; 3] = [3, 0, 6];
    // player gets 1/2/3 points for playing R/P/S respectively, don't need an array for that

    let total_score: i64 = input
        .split('\n')
        .fold(0, |mut acc, x| {
            let mut x: Chars<'_> = x.chars();

            // converting R/P/S to 0/1/2
            let them = (x.next().unwrap() as i64) - 65;
            x.next();
            let us = (x.next().unwrap() as i64) - 88;

            // adding points to accumulator
            acc = acc + outcome_points[(((us * 2) + them) % 3) as usize] + (us + 1);

            acc
        });

    total_score
}

// Player still gets 0/3/6 points for loss/draw/win
// Player still gets 1/2/3 points for playing R/P/S

pub fn b (input_file: &str) -> i64 {
    let input = fs::read_to_string(input_file).expect("Unable to read file.");

    let mut input = input;
    input.pop();

    // array for required moves, 2/0/1 correspond to S/R/P respectively
    let req_move: [i64; 3] = [2, 0, 1];

    let total_score: i64 = input
        .split('\n')
        .fold(0, |mut acc, x| {
            let mut x: Chars<'_> = x.chars();

            // converting R/P/S to 0/1/2
            let them = (x.next().unwrap() as i64) - 65;
            x.next();
            let outcome = (x.next().unwrap() as i64) - 88;

            // adding points to accumulator
            acc = acc + (req_move[((them + outcome) % 3) as usize] + 1) + (outcome * 3);

            acc
        });

    total_score
}
