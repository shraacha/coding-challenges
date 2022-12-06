pub fn sol1 () {
    let input = include_str!("../inputs/day03.txt");

    let output = input
        .split_terminator('\n')
        .fold(0, |mut acc, x| {
            let sack_size = x.len();                        // getting the length of the line
            let (left, right) = x.split_at(sack_size / 2);  // splitting the line into compartments

            let left_contents: u64 = left                   // bits in this u64 represent which letters are present in left compartment
                .chars()
                .fold(0, |acc, y| {
                    let shift = match y as u64 {
                        65..=90 => (y as u64) - 65 + 26,
                        _ => (y as u64) - 97,
                    };

                    acc | 0b1u64 << shift                   // setting the Nth bit (a..zA..Z corresspond to the 0th..51st bits)
                });

            for letter in right.chars() {
                let shift = match letter as u64 {
                    65..=90 => (letter as u64) - 65 + 26,
                    _ => (letter as u64) - 97,
                };

                if ((left_contents & (0b1u64 << shift)) >> shift) != 0 {
                    acc += shift + 1;
                    break;
                }
            }

            //println!("{left_contents:#064b}");              // for debugging

            acc
        });

    println!("day03 sol1: {}", output);
}
