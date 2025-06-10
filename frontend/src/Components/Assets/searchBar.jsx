import React, { useState, useEffect } from "react";
import { fetchPlayerNames } from "../Services/playerNameService";

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

function SearchBar() {
  const [searchTerm, setSearchTerm] = useState("");
  const [allSuggestions, setAllSuggestions] = useState([]);
  const [filteredSuggestions, setFilteredSuggestions] = useState([]);
  const [showSuggestions, setShowSuggestions] = useState(false);
  
  useEffect(() => {
    const loadSuggestions = async () => {
      const players = await fetchPlayerNames("premier_league_players_table");
      console.log("Fetched players:", players);
      setAllSuggestions(players);
    };

    loadSuggestions();
  }, []);

  const handleChange = (e) => {
    const userInput = e.target.value;
    setSearchTerm(userInput);

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
    setFilteredSuggestions([]);
    setShowSuggestions(false);
  };

  return (
    <div className="relative w-64 mx-auto mt-10">
      <input
        type="text"
        className="w-full p-2 border border-gray-300 rounded"
        placeholder="Search for a fruit..."
        value={searchTerm}
        onChange={handleChange}
      />
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
