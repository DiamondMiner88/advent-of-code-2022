use regex::{Captures, Regex};

#[derive(Debug)]
struct Monkey {
    items: Vec<usize>,
    operation: String,
    divisible_by: i32,
    target_monkey_true: i32,
    target_monkey_false: i32,
    total_inspections: i32,
}

fn main() {
    let input = include_str!("../../input/day11.txt");
//     let input = "\
// Monkey 0:
//   Starting items: 79, 98
//   Operation: new = old * 19
//   Test: divisible by 23
//     If true: throw to monkey 2
//     If false: throw to monkey 3
//
// Monkey 1:
//   Starting items: 54, 65, 75, 74
//   Operation: new = old + 6
//   Test: divisible by 19
//     If true: throw to monkey 2
//     If false: throw to monkey 0
//
// Monkey 2:
//   Starting items: 79, 60, 97
//   Operation: new = old * old
//   Test: divisible by 13
//     If true: throw to monkey 1
//     If false: throw to monkey 3
//
// Monkey 3:
//   Starting items: 74
//   Operation: new = old + 3
//   Test: divisible by 17
//     If true: throw to monkey 0
//     If false: throw to monkey 1";

    let regex = "Monkey (\\d):\\r?\n  Starting items: (.+?)\\r?\n  Operation: new = old (.+?)\\r?\n  Test: divisible by (\\d{1,2})\\r?\n    If true: throw to monkey (\\d)\\r?\n    If false: throw to monkey (\\d)";
    let regex = Regex::new(regex).unwrap();

    let mut monkeys: Vec<Monkey> = Vec::new();
    for capture in regex.captures_iter(input) {
        let items: Vec<&str> = capture.get(2).unwrap().as_str().split(",").collect();
        let items = items.iter().map(|i| i.trim().parse::<usize>().unwrap()).collect();
        let monk = Monkey {
            items,
            operation: capture.get(3).unwrap().as_str().to_string(),
            divisible_by: capture.get(4).unwrap().as_str().parse::<i32>().unwrap(),
            target_monkey_true: capture.get(5).unwrap().as_str().parse::<i32>().unwrap(),
            target_monkey_false: capture.get(6).unwrap().as_str().parse::<i32>().unwrap(),
            total_inspections: 0,
        };
        monkeys.push(monk);
    }

    let divisor: usize = monkeys.iter().fold(1, |x, y| x * y.divisible_by as usize);

    for _ in 1..=10000 {
        for monkey_id in 0..monkeys.len() {
            let mut items: Option<Vec<usize>> = None;
            let mut divisible_by: Option<i32> = None;
            let mut target_true: Option<i32> = None;
            let mut target_false: Option<i32> = None;

            {
                let monkey = monkeys.get_mut(monkey_id).unwrap();
                let op_parts: Vec<&str> = monkey.operation.split(" ").collect();
                let op_num = op_parts[1].parse::<usize>().unwrap_or(0);
                let op = op_parts[0];

                for item in &mut monkey.items {
                    match op {
                        "*" => *item *= if op_num == 0 { item.to_owned() } else { op_num },
                        "+" => *item += if op_num == 0 { item.to_owned() } else { op_num },
                        _ => {}
                    }

                    // thanks ven i should've read your answer first GUHHHHHHHHHHHHHHHHHHHHHHHH
                    *item %= divisor;

                    monkey.total_inspections += 1;
                }


                divisible_by = Some(monkey.divisible_by);
                target_true = Some(monkey.target_monkey_true);
                target_false = Some(monkey.target_monkey_false);
                items = Some(monkey.items.clone());
                monkey.items.clear();
            }

            for item in items.unwrap() {
                if item % divisible_by.unwrap() as usize == 0 {
                    let monk = monkeys.get_mut(target_true.unwrap() as usize).unwrap();
                    monk.items.push(item.clone());
                } else {
                    let monk = monkeys.get_mut(target_false.unwrap() as usize).unwrap();
                    monk.items.push(item.clone());
                }
            }
        }
    }

    monkeys.sort_by(|m, m2| m2.total_inspections.cmp(&m.total_inspections));
    let answer = monkeys[0].total_inspections * monkeys[1].total_inspections;
    println!("{}", answer);
}
