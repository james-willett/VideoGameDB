package com.techietester.resource;

import com.techietester.model.VideoGame;
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
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class VideoGameResource {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public VideoGameResource(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @GET
    public List<VideoGame> listVideoGames() {

        String sql = "select * from VIDEOGAME";

        return namedParameterJdbcTemplate.query(sql, new VideoGameMapper());
    }

    @GET
    @Path("/{videoGameId}")
    public VideoGame getVideoGame(@PathParam("videoGameId") Integer videoGameId) {
        String sql = "select * from VIDEOGAME where id=:videoGameId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("videoGameId", videoGameId);
        return namedParameterJdbcTemplate.query(sql, namedParameters, new VideoGameMapper()).get(0);
    }

    @POST
    public String createVideoGame(final VideoGame videoGame) {
        String sql = "insert into VIDEOGAME values(DEFAULT, :name, :releaseDate, :reviewScore, :category, :rating)";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(videoGame);
        this.namedParameterJdbcTemplate.update(sql, namedParameters);
        return "{\"status\": \"Record Added Successfully\"}";
    }

    @PUT
    @Path("/{videoGameId}")
    public VideoGame editVideoGame(final VideoGame videoGame, @PathParam("videoGameId") Integer videoGameId) {
        String sql = "update VIDEOGAME set name=:name, released_on=:releaseDate, review_score=:reviewScore, category=:category, rating=:rating where id=:id";
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(videoGame);
        this.namedParameterJdbcTemplate.update(sql, namedParameters);

        sql = "select * from VIDEOGAME where id=:videoGameId";
        SqlParameterSource namedParameters2 = new MapSqlParameterSource("videoGameId", videoGameId);
        return namedParameterJdbcTemplate.query(sql, namedParameters2, new VideoGameMapper()).get(0);
    }

    @DELETE
    @Path("/{videoGameId}")
    public String deleteVideoGame(@PathParam("videoGameId") Integer videoGameId) {
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
