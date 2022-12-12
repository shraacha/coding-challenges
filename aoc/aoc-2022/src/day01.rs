use std::fs;

pub fn a(input_file: &str) -> i64{
    let input = fs::read_to_string(input_file).expect("Unable to read file.");

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

    //println!("day01 a: {max}");
    max
}

pub fn b(input_file: &str) -> i64 {
    let input = fs::read_to_string(input_file).expect("Unable to read file.");

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

    // println!("day01 b: {}", sum);
    let sum: i64 = maxes.iter().sum();
    sum
}
