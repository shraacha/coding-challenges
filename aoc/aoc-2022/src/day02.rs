use::core::str::Chars;

pub fn a () {
    let mut input = include_str!("../inputs/day02.txt").to_string(); // getting input as a string, cannot pop the last newline without `to_string`, figure out why

    // using pop to get rid of trailing newline
    input.pop();

    // points handed out for draw/loss/win respectively
    let outcome_points: [u64; 3] = [3, 0, 6];
    // player gets 1/2/3 points for playing R/P/S respectively, don't need an array for that

    let total_score: u64 = input
        .split('\n')
        .fold(0, |mut acc, x| {
            let mut x: Chars<'_> = x.chars();

            // converting R/P/S to 0/1/2
            let them = (x.next().unwrap() as u64) - 65;
            x.next();
            let us = (x.next().unwrap() as u64) - 88;

            // adding points to accumulator
            acc = acc + outcome_points[(((us * 2) + them) % 3) as usize] + (us + 1);

            acc
        });

    println!("day02 a: {}", total_score);
}

// Player still gets 0/3/6 points for loss/draw/win
// Player still gets 1/2/3 points for playing R/P/S

pub fn b () {
    let mut input = include_str!("../inputs/day02.txt").to_string();

    input.pop();

    // array for required moves, 2/0/1 correspond to S/R/P respectively
    let req_move: [u64; 3] = [2, 0, 1];

    let total_score: u64 = input
        .split('\n')
        .fold(0, |mut acc, x| {
            let mut x: Chars<'_> = x.chars();

            // converting R/P/S to 0/1/2
            let them = (x.next().unwrap() as u64) - 65;
            x.next();
            let outcome = (x.next().unwrap() as u64) - 88;

            // adding points to accumulator
            acc = acc + (req_move[((them + outcome) % 3) as usize] + 1) + (outcome * 3);

            acc
        });

    println!("day02 b: {}", total_score);

}
