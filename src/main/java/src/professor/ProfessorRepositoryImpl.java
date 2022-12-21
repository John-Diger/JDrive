package src.professor;

import org.apache.commons.io.FileUtils;
import src.ConnectionManager;
import src.professor.entity.Bucket;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ProfessorRepositoryImpl implements ProfessorRepository {

    @Override
    public void insert(Object file) throws IOException {
        final String query = "INSERT INTO bucket(name, shared_file, created_at, modified_at, status) VALUES(?,?,?,?,?)";
        byte[] fileToByteArray = FileUtils.readFileToByteArray((File) file);
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, ((File) file).getName());
            preparedStatement.setBytes(2, fileToByteArray);
            preparedStatement.setDate(3, new Date(System.currentTimeMillis()));
            preparedStatement.setDate(4, new Date(System.currentTimeMillis()));
            preparedStatement.setBoolean(5, Boolean.TRUE);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Bucket> findAll() {
        List<Bucket> result = new ArrayList<>();
        String query = "SELECT * FROM bucket";
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()) {
                Bucket loaded = new Bucket();
                loaded.setId(resultSet.getLong("id"));
                loaded.setSharedFile(resultSet.getBytes("shared_file"));
                loaded.setCreatedAt(resultSet.getDate("created_at").toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime());
                loaded.setStatus(resultSet.getBoolean("status"));
                result.add(loaded);
                resultSet.close();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Bucket findById(long id) {
        Bucket bucket = new Bucket();
        String query = "SELECT * FROM bucket WHERE id = ?";
        try (PreparedStatement preparedStatement = ConnectionManager.getConnection().prepareStatement(query)) {
            ResultSet resultSet;
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery(query);
            bucket.setId(resultSet.getLong("id"));
            bucket.setSharedFile(resultSet.getBytes("shared_file"));
            bucket.setCreatedAt(resultSet.getDate("created_at").toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime());
            bucket.setStatus(resultSet.getBoolean("status"));
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return bucket;
    }
}
