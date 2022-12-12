use std::fs;

pub fn a (input_file: &str) -> i32 {
    let input = fs::read_to_string(input_file).expect("Unable to read file.");

    let mut output = 0;

    // yay, no input shenanigans!

    // solution is unoptimized
    let code_size = 4;
    for i in code_size..=input.len() {
        if all_unique(input.get(i - code_size..i).unwrap().to_string().chars().collect()) {
            output = i;
            break;
        }
    }

    output as i32
}

// only solution difference from part a is code size
pub fn b (input_file: &str) -> i32 {
    let input = fs::read_to_string(input_file).expect("Unable to read file.");

    let mut output = 0;

    // yay, no input shenanigans!

    // solution is unoptimized
    let code_size = 14;
    for i in code_size..=input.len() {
        if all_unique(input.get(i - code_size..i).unwrap().to_string().chars().collect()) {
            output = i;
            break;
        }
    }

    output as i32
}

// recursive function to check whether all chars in the vector are unique or not
fn all_unique(mut code: Vec<char>) -> bool {
    if code.is_empty() {
        true
    } else {
        let curr = code.pop().unwrap();
        for l in code.iter() {
            if curr == *l {
                return false;
            }
        }

        all_unique(code)
    }
}
