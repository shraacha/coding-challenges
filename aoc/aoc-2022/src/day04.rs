use std::fs;

pub fn a (input_file: &str) -> i64 {
    let input = fs::read_to_string(input_file).expect("Unable to read file.");

    let output = input
        .split_terminator('\n')
        .fold(0, |acc, x| {
            // there is probably a better way to take in the input...
            let mut x = x.split(',');

            let mut left = x.next().unwrap().split('-');
            let mut right = x.next().unwrap().split('-');

            let (a, b, x, y) = (left.next().unwrap().parse::<i64>().unwrap(),
                                left.last().unwrap().parse::<i64>().unwrap(),
                                right.next().unwrap().parse::<i64>().unwrap(),
                                right.last().unwrap().parse::<i64>().unwrap());

            // if one range fully contains the other, say A contains B,
            // the left end of A should be <= the left end of B,
            // the right end of A should be >= the right end of B,
            // i.e. multiplying the differences in indexes of these ends
            // gives us a result <= 0 if one range contains the other
            //
            // could also check if l1 > r2 or if l2 > r2, quite simple
            if (a - x) * (b - y) <= 0 {
                acc + 1
            } else {
                acc
            }
        });

    output
}

pub fn b (input_file: &str) -> i64 {
    let input = fs::read_to_string(input_file).expect("Unable to read file.");

    let output = input
        .split_terminator('\n')
        .fold(0, |acc, x| {
            // there is probably a better way to take in the input...
            let mut x = x.split(',');

            let mut left = x.next().unwrap().split('-');
            let mut right = x.next().unwrap().split('-');

            let (a, b, x, y) = (left.next().unwrap().parse::<i64>().unwrap(),
                                left.last().unwrap().parse::<i64>().unwrap(),
                                right.next().unwrap().parse::<i64>().unwrap(),
                                right.last().unwrap().parse::<i64>().unwrap());

            // similar concept as in part a, except we compare the left end of one
            // range with the right end of the other range.
            // if both differences !=0 and have the same sign, then the ranges are disjoint
            if (a - y) * (b - x) <= 0 {
                acc + 1
            } else {
                acc
            }
        });

    output
}
