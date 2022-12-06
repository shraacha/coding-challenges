pub fn a() {
    let input = include_str!("../inputs/day01.txt");

    //println!("To the joker this is regular input: \n{input}");

    let mut max: i64 = 0;

    for bag in input.split("\n\n") {
        let mut curr: i64 = 0;

        for item in bag.split('\n') {
            let item: i64 = match item.trim().parse() {
                Ok(num) => num,
                Err(_) => break,
            };
            curr += item;
        }

        if max < curr {
            max = curr;
        }
    }

    println!("day01 a: {max}");
}

pub fn b() {
    let input = include_str!("../inputs/day01.txt");

    let mut maxes = vec![0, 0, 0];

    for bag in input.split("\n\n") {
        let mut curr: i64 = 0;

        for item in bag.split('\n') {
            let item: i64 = match item.trim().parse() {
                Ok(num) => num,
                Err(_) => break,
            };
            curr += item;
        }

        for i in 0..maxes.len() {
            if curr > maxes[i] {
                maxes.insert(i, curr);
                maxes.pop();
                break;
            }
        }
    }

    let sum: i64 = maxes.iter().sum();
    println!("day01 b: {}", sum);
}
