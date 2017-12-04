package com.techietester.resource;

import com.techietester.model.VideoGame;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/videogames")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,})
@Api(value = "Video Games")
public class VideoGameResource {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public VideoGameResource(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @GET
    @ApiOperation(value = "Get List of all Video Games", notes = "Returns all the videos games in the DB", response = VideoGame.class, responseContainer = "List")
    public List<VideoGame> listVideoGames() {

        String sql = "select * from VIDEOGAME";

        return namedParameterJdbcTemplate.query(sql, new VideoGameMapper());
    }

    @GET
    @Path("/{videoGameId}")
    @ApiOperation(value = "Get a single video game by ID", notes = "Returns the details of a single game by ID", response = VideoGame.class)
    public VideoGame getVideoGame(
            @ApiParam(value = "The video game ID", required = true) @PathParam("videoGameId") Integer videoGameId)
    {
        String sql = "select * from VIDEOGAME where id=:videoGameId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("videoGameId", videoGameId);
        return namedParameterJdbcTemplate.query(sql, namedParameters, new VideoGameMapper()).get(0);
    }

    @POST
    @ApiOperation(value = "Add a new video game", notes = "Add a new video game to the DB")
    public String createVideoGame(final VideoGame videoGame) {
        String sql = "insert into VIDEOGAME values(:id, :name, :releaseDate, :reviewScore, :category, :rating)";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(videoGame);
        this.namedParameterJdbcTemplate.update(sql, namedParameters);
        return "{\"status\": \"Record Added Successfully\"}";
    }

    @PUT
    @Path("/{videoGameId}")
    @ApiOperation(value = "Update a video game", notes = "Update an existing video game in the DB by specifying a new body ", response = VideoGame.class)
    public VideoGame editVideoGame(final VideoGame videoGame, @PathParam("videoGameId") Integer videoGameId ) {
        String sql = "update VIDEOGAME set id=:id, name=:name, released_on=:releaseDate, review_score=:reviewScore, category=:category, rating=:rating where id=:id";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(videoGame);
        this.namedParameterJdbcTemplate.update(sql, namedParameters);

        sql = "select * from VIDEOGAME where id=:videoGameId";
        SqlParameterSource namedParameters2 = new MapSqlParameterSource("videoGameId", videoGameId);
        return namedParameterJdbcTemplate.query(sql, namedParameters2, new VideoGameMapper()).get(0);
    }

    @DELETE
    @Path("/{videoGameId}")
    @ApiOperation(value = "Delete a video game", notes = "Deletes a video game from the DB by ID")
    public String deleteVideoGame(
            @ApiParam(value = "The video game ID", required = true) @PathParam("videoGameId") Integer videoGameId)
    {
        String sql = "delete from VIDEOGAME where id =:videoGameId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("videoGameId", videoGameId);
        this.namedParameterJdbcTemplate.update(sql, namedParameters);
        return "{\"status\": \"Record Deleted Successfully\"}";
    }


    private static final class VideoGameMapper implements RowMapper<VideoGame> {
        public VideoGame mapRow(ResultSet rs, int rowNum) throws SQLException {

            VideoGame videoGame = new VideoGame();
            videoGame.setId(rs.getInt("id"));
            videoGame.setName(rs.getString("name"));
            videoGame.setReleaseDate(rs.getDate("released_on"));
            videoGame.setReviewScore(rs.getInt("review_score"));
            videoGame.setCategory(rs.getString("category"));
            videoGame.setRating(rs.getString("rating"));
            return videoGame;
        }
    }
}
