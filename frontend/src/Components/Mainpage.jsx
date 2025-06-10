import Button from 'react-bootstrap/Button';
import LeageButtons from './Assets/LeagueButtons';
import SearchBar from './Assets/SearchBar';
import { useState } from 'react';
import './Mainpage.css'
//main page of the frontend
function Mainpage() {
     const [selectedLeague, setSelectedLeague] = useState("premier_league_players_table");
  return (
    <>
    <div className='main-container'>
    <LeageButtons onLeagueChange={setSelectedLeague}></LeageButtons>
      <SearchBar leagueName={selectedLeague}></SearchBar>

    </div>
     
    </>
  );
}
export default Mainpage;