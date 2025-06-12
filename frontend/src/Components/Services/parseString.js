


export const parseString = (string) => {
    console.log("Parsing ", string);
     if (!string) return "";

  //add a space before any uppercase letter, then capitalize the first letter
  const result = string
    .replace(/([A-Z])/g, " $1") //add space before capitals
    .replace(/^./, (str) => str.toUpperCase()); //  capitalize first letter

    return result;
    }