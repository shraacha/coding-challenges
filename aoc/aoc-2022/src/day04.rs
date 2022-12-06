pub fn a () {
    let input = include_str!("../inputs/day04.txt");

    let output = input
        .split_terminator('\n')
        .fold(0, |acc, x| {
            // there is probably a better way to take in the input...
            let mut x = x.split(',');

            let mut left = x.next().unwrap().split('-');
            let mut right = x.next().unwrap().split('-');

            let (a, b, x, y) = (left.next().unwrap().parse::<i32>().unwrap(),
                                left.last().unwrap().parse::<i32>().unwrap(),
                                right.next().unwrap().parse::<i32>().unwrap(),
                                right.last().unwrap().parse::<i32>().unwrap());

            // if one range fully contains the other, say A contains B,
            // the left end of A should be <= the left end of B,
            // the right end of A should be >= the right end of B,
            // i.e. multiplying the differences in indexes of these ends
            // gives us a result <= 0 if one range contains the other
            if (a - x) * (b - y) <= 0 {
                acc + 1
            } else {
                acc
            }
        });

    println!("day04 a: {}", output);
}

pub fn b () {
    let input = include_str!("../inputs/day04.txt");

    let output = input
        .split_terminator('\n')
        .fold(0, |acc, x| {
            // there is probably a better way to take in the input...
            let mut x = x.split(',');

            let mut left = x.next().unwrap().split('-');
            let mut right = x.next().unwrap().split('-');

            let (a, b, x, y) = (left.next().unwrap().parse::<i32>().unwrap(),
                                left.last().unwrap().parse::<i32>().unwrap(),
                                right.next().unwrap().parse::<i32>().unwrap(),
                                right.last().unwrap().parse::<i32>().unwrap());

            // similar concept as in part a, except we compare the left end of one
            // range with the right end of the other range.
            // if both differences !=0 and have the same sign, then the ranges are disjoint
            if (a - y) * (b - x) <= 0 {
                acc + 1
            } else {
                acc
            }
        });

    println!("day04 b: {}", output);

}
