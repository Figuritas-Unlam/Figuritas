# Figuritas
## APIs
Se especifica las APIs que se utilizan en el proyecto y sus endpoints.
### Sportmonks
Obtiene los jugadores (con sus características), equipos y paises. 
- Api Token = 3puVV9TkLHqxgI5xrd00AOS16Zxs2IdcSJLhGciUdRuzswDOvN4KuUVtZuHo
- Jugadores por equipo = https://api.sportmonks.com/v3/football/squads/teams/{EquipoId}?api_token={{api_token}}&include=player
- Jugador por ID = https://api.sportmonks.com/v3/football/players/{JugadorId}?api_token={{api_token}}&include=
- Jugador por nombre = https://api.sportmonks.com/v3/football/search/{JugadorNombre}?api_token={{api_token}}&include=
- Equipo por nombre = https://api.sportmonks.com/v3/football/teams/search/{ClubNombre}?api_token={{api_token}}&include=
- Equipo por Id = https://api.sportmonks.com/v3/football/teams/{EquipoId}?api_token={{api_token}}&include=
- Pais por nombre = https://api.sportmonks.com/v3/core/countries/search/{PaisNombre}?api_token={{api_token}}&include=ç
Documentacion: https://docs.sportmonks.com/football
