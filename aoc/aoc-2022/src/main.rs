#![allow(dead_code)]
#![feature(int_log)]

mod day01;
mod day02;
mod day03;
mod day04;
mod day05;
mod day06;
mod day07;
mod day08;

fn main() {
    //println!("day01 a: {}", day01::a("./inputs/day01.txt"));
    //println!("day01 b: {}", day01::b("./inputs/day01.txt"));

    //println!("day02 a: {}", day02::a("./inputs/day02.txt"));
    //println!("day02 b: {}", day02::b("./inputs/day02.txt"));

    //println!("day03 a: {}", day03::a("./inputs/day03.txt"));
    //println!("day03 b: {}", day03::b("./inputs/day03.txt"));

    //println!("day04 a: {}", day04::a("./inputs/day04.txt"));
    //println!("day04 b: {}", day04::b("./inputs/day04.txt"));

    //println!("day05 a: {}", day05::a("./inputs/day05.txt"));
    //println!("day05 b: {}", day05::b("./inputs/day05.txt"));

    //println!("day06 a: {}", day06::a("./inputs/day06.txt"));
    //println!("day06 b: {}", day06::b("./inputs/day06.txt"));

    //println!("day07 a: {}", day07::a("./inputs/day07.txt"));
    //println!("day07 b: {}", day07::b("./inputs/day07.txt"));

    //println!("day08 a: {}", day08::a("./inputs/day08.txt"));
    //println!("day08 b: {}", day08::b("./inputs/day08.txt"));
}

#[cfg(test)]
mod day01_tests {
    use super::day01;

    #[test]
    fn day01a_ex() {assert_eq!(24000, day01::a("./inputs/day01_ex.txt"));}
    #[test]
    fn day01a() {assert_eq!(68775, day01::a("./inputs/day01.txt"));}
    #[test]
    fn day01b_ex() {assert_eq!(45000, day01::b("./inputs/day01_ex.txt"));}
    #[test]
    fn day01b() {assert_eq!(202585, day01::b("./inputs/day01.txt"));}
}

#[cfg(test)]
mod day02_tests {
    use super::day02;

    #[test]
    fn day02a_ex() {assert_eq!(15, day02::a("./inputs/day02_ex.txt"));}
    #[test]
    fn day02a() {assert_eq!(9241, day02::a("./inputs/day02.txt"));}
    #[test]
    fn day02b_ex() {assert_eq!(12, day02::b("./inputs/day02_ex.txt"));}
    #[test]
    fn day02b() {assert_eq!(14610, day02::b("./inputs/day02.txt"));}
}

#[cfg(test)]
mod day03_tests {
    use super::day03;

    #[test]
    fn day03a_ex() {assert_eq!(157, day03::a("./inputs/day03_ex.txt"));}
    #[test]
    fn day03a() {assert_eq!(8252, day03::a("./inputs/day03.txt"));}
    #[test]
    fn day03b_ex() {assert_eq!(70, day03::b("./inputs/day03_ex.txt"));}
    #[test]
    fn day03b() {assert_eq!(2828, day03::b("./inputs/day03.txt"));}
}

#[cfg(test)]
mod day04_tests {
    use super::day04;

    #[test]
    fn day04a_ex() {assert_eq!(2, day04::a("./inputs/day04_ex.txt"));}
    #[test]
    fn day04a() {assert_eq!(576, day04::a("./inputs/day04.txt"));}
    #[test]
    fn day04b_ex() {assert_eq!(4, day04::b("./inputs/day04_ex.txt"));}
    #[test]
    fn day04b() {assert_eq!(905, day04::b("./inputs/day04.txt"));}
}

#[cfg(test)]
mod day05_tests {
    use super::day05;

    #[test]
    fn day05a_ex() {assert_eq!("CMZ".to_string(), day05::a("./inputs/day05_ex.txt"));}
    #[test]
    fn day05a() {assert_eq!("MQSHJMWNH".to_string(), day05::a("./inputs/day05.txt"));}
    #[test]
    fn day05b_ex() {assert_eq!("MCD".to_string(), day05::b("./inputs/day05_ex.txt"));}
    #[test]
    fn day05b() {assert_eq!("LLWJRBHVZ".to_string(), day05::b("./inputs/day05.txt"));}
}

#[cfg(test)]
mod day06_tests {
    use super::day06;

    #[test]
    fn day06a_ex() {assert_eq!(7, day06::a("./inputs/day06_ex.txt"));}
    #[test]
    fn day06a() {assert_eq!(1892, day06::a("./inputs/day06.txt"));}
    #[test]
    fn day06b_ex() {assert_eq!(19, day06::b("./inputs/day06_ex.txt"));}
    #[test]
    fn day06b() {assert_eq!(2313, day06::b("./inputs/day06.txt"));}
}

#[cfg(test)]
mod day07_tests {
    use super::day07;

    #[test]
    fn day07a_ex() {assert_eq!(95437, day07::a("./inputs/day07_ex.txt"));}
    #[test]
    fn day07a() {assert_eq!(1182909, day07::a("./inputs/day07.txt"));}
    #[test]
    fn day07b_ex() {assert_eq!(24933642, day07::b("./inputs/day07_ex.txt"));}
    #[test]
    fn day07b() {assert_eq!(2832508, day07::b("./inputs/day07.txt"));}
}

#[cfg(test)]
mod day08_tests {
    use super::day08;

    #[test]
    fn day08a_ex() {assert_eq!(21, day08::a("./inputs/day08_ex.txt"));}
    #[test]
    fn day08a() {assert_eq!(1789, day08::a("./inputs/day08.txt"));}
    #[test]
    fn day08b_ex() {assert_eq!(8, day08::b("./inputs/day08_ex.txt"));}
    #[test]
    fn day08b() {assert_eq!(314820, day08::b("./inputs/day08.txt"));}
}
