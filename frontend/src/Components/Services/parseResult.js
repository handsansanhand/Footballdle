
import { ImCross } from "react-icons/im";
import { ImCheckmark } from "react-icons/im";
import { ImArrowUp } from "react-icons/im";
import { ImArrowDown } from "react-icons/im";


export const parseResult = (string) => {
    console.log("Parsing in result for ", string);
    switch (string) {
        case ("Higher") : 
            return <ImArrowUp />
        case ("Lower") : 
            return <ImArrowDown />
        case ("Correct"):
            return <ImCheckmark color="green"/>
        case ("Incorrect"):
            return <ImCross color="red"/>
        default:
            return string;
    }
    }