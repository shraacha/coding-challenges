#![feature(int_log)]
use std::fs;

#[inline]
fn get_letter_val(letter: char) -> u64 {
    match letter {
        'a'..='z' => (letter as u64) - 97,       // capital letters are 26-51
        'A'..='Z' => (letter as u64) - 65 + 26,  // lower-case letters ar 0-25
        _ => unreachable!(),
    }
}

pub fn a (input_file: &str) -> i64 {
    let input = fs::read_to_string(input_file).expect("Unable to read file.");

    let output = input
        .split_terminator('\n')
        .fold(0, |mut acc: i64, x| {
            let sack_size = x.len();                        // getting the length of the line
            let (left, right) = x.split_at(sack_size / 2);  // splitting the line into left and right compartments

            // parsing left
            let left_contents: u64 = left                   // finding what letters are in the left_compartment
                .chars()                                    // bits in this u64 represent which letters are present in left compartment, a..z map to bits 0..25, and A..Z map to bits 26..51
                .fold(0, |left_contents_acc, y| {
                    let shift = get_letter_val(y);          // getting the value of the letter

                    left_contents_acc | 0b1u64 << shift     // setting the Nth bit (a..zA..Z corresspond to the 0th..51st bits)
                });

            // parsing right
            for letter in right.chars() {
                let shift = get_letter_val(letter);                // getting the value of the letter
                let intersect = left_contents & (0b1u64 << shift); // AND-ing the shifted value for current letter with the u64 above to see if the letter is on the left

                if intersect != 0 {
                    acc += (shift + 1) as i64;
                    break;
                }
            }

            //println!("{left_contents:#064b}");              // for debugging

            acc
        });

    output
}

pub fn b (input_file: &str) -> i64 {
    let input = fs::read_to_string(input_file).expect("Unable to read file.");

    let output = input
        .split_terminator('\n')
        .collect::<Vec<_>>()
        .chunks(3)                  // splitting lines into groups of 3
        .fold(0, |mut acc1, x|{     // iterating over all of the groups
            let badge = x.iter()    // iterating over elves in each group
                .fold(!0b0u64, |acc2, y| {                  // want to keep all items in first sack, so init acc is max int
                    let curr_bag = y.chars()                // getting a new binary number representing the items in the current bag
                        .fold(0b0u64, |acc3, z| {
                            let shift = get_letter_val(z);  // getting the value of the letter

                            acc3 | 0b1u64 << shift
                        });

                    acc2 & curr_bag // AND-ing the last bag and the current bag contents
                })
                .ilog2();           // the resulting number should be a neat power of 2, or 0, corresponding to the badge letter, or the lack of one

            acc1 += badge + 1;

            acc1
        });

    output as i64
}
