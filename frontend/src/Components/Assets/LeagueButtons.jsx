import './LeagueButtons.css'
import * as React from 'react';
import Button from 'react-bootstrap/Button';
import ToggleButton from '@mui/material/ToggleButton';
import ToggleButtonGroup from '@mui/material/ToggleButtonGroup';
//component which contains the main league buttons
function LeageButtons( {onLeagueChange} ) {
      const [selectedLeague, setSelectedLeague] = React.useState("all_leagues_players_table");
  const handleLeagueChange = (event, newLeague) => {
    if (newLeague !== null) {
      setSelectedLeague(newLeague);
      onLeagueChange(newLeague); 
    }
  };
    return (
        <>
        <div className="button-list-container">
    <ToggleButtonGroup
    className="toggle-button-group"
      value={selectedLeague}
      exclusive
      onChange={handleLeagueChange}
      aria-label="text alignment"
    >
       <ToggleButton value="overall_players_table">All Leagues</ToggleButton>
      <ToggleButton value="premier_league_players_table">Premier League</ToggleButton>
      <ToggleButton value="la_liga_players_table">La Liga</ToggleButton>
      <ToggleButton value="ligue_1_players_table">Ligue 1</ToggleButton>
      <ToggleButton value="bundesliga_players_table">Bundesliga</ToggleButton>
      <ToggleButton value="serie_a_players_table">Serie A</ToggleButton>

    </ToggleButtonGroup>
        </div>
         </>
    );
}

export default LeageButtons;