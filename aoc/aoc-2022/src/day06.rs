pub fn a () {
    let input = include_str!("../inputs/day06.txt").to_string();

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

    println!("day06 a: {output}");
}

// only solution difference from part a is code size
pub fn b () {
    let input = include_str!("../inputs/day06.txt");

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

    println!("day06 b: {output}");
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
