import React, { useState, useEffect } from "react";
import { fetchPlayerNames } from "../Services/playerNameService";
import { makeGuess } from "../Services/makeGuess";
import './SearchBar.css'

import Button from "react-bootstrap/esm/Button";
const suggestionsList = [
  "Apple",
  "Banana",
  "Cherry",
  "Durian",
  "Grape",
  "Mango",
  "Pineapple",
  "Peach",
  "Strawberry"
];

function SearchBar({leagueName, onGuessMade}) {
  const [searchTerm, setSearchTerm] = useState("");
  const [selectedPlayer, setSelectedPlayer] = useState("");
  const [allSuggestions, setAllSuggestions] = useState([]);
  const [filteredSuggestions, setFilteredSuggestions] = useState([]);
  const [showSuggestions, setShowSuggestions] = useState(false);
  
  useEffect(() => {
    const loadSuggestions = async () => {
       console.log("Fetching players for :", leagueName);  
      const players = await fetchPlayerNames(leagueName);
      if(players.length == 0) {
        console.log("errorerror");
        setAllSuggestions(suggestionsList);
      }
      setAllSuggestions(players);
    };

    loadSuggestions();
  }, [leagueName]);

  const handleChange = (e) => {
    const userInput = e.target.value;
   
    setSearchTerm(userInput);
     setSelectedPlayer("");

    if (userInput.length === 0) {
      setFilteredSuggestions([]);
      setShowSuggestions(false);
      return;
    }

    const filtered = allSuggestions.filter((item) =>
      item.toLowerCase().includes(userInput.toLowerCase())
    )
    .slice(0,15);

    setFilteredSuggestions(filtered);
    setShowSuggestions(true);
  };

  const handleSuggestionClick = (suggestion) => {
    setSearchTerm(suggestion);
    setSelectedPlayer(suggestion);
    setFilteredSuggestions([]);
    setShowSuggestions(false);
  };

const handleMakeGuess = async () => {
    if (!selectedPlayer) return;

    const result = await makeGuess(selectedPlayer, leagueName);
    if (onGuessMade) {
      onGuessMade(typeof result === 'string' ? result : JSON.stringify(result));
    }
  };

  return (
    <div className="search-bar-container mt-10">
      <input
        type="text"
        className="search-input"
        placeholder="Search for a player..."
        value={searchTerm}
        onChange={handleChange}
      />
      <Button className="search-button" 
      onClick={handleMakeGuess}
       disabled={!selectedPlayer}
      >Search</Button>
      {showSuggestions && filteredSuggestions.length > 0 && (
        <ul className="absolute z-10 w-full bg-white border border-gray-300 rounded mt-1 max-h-40 overflow-y-auto">
          {filteredSuggestions.map((suggestion, index) => (
            <li
              key={index}
              className="p-2 cursor-pointer hover:bg-gray-100"
              onClick={() => handleSuggestionClick(suggestion)}
            >
              {suggestion}
            </li>
          ))}
        </ul>
      )}
     
    </div>
  );
}

export default SearchBar;
