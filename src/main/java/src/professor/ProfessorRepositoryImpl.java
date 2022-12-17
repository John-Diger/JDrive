package src.professor;

import src.JavaBeanFactory;
import src.professor.entity.Bucket;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ProfessorRepositoryImpl implements ProfessorRepository {

    @Override
    public void insert(Bucket bucket) {
        final String query = "INSERT INTO bucket(shared_file, created_at, modified_at, status) VALUES(?,?,?,?)";
        try (PreparedStatement preparedStatement = JavaBeanFactory.getConnection().prepareStatement(query)) {
            preparedStatement.setBlob(1, bucket.getSharedFile());
            preparedStatement.setDate(2, new Date(System.currentTimeMillis()));
            preparedStatement.setDate(3, new Date(System.currentTimeMillis()));
            preparedStatement.setBoolean(4, Boolean.TRUE);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Bucket> findAll() {
        List<Bucket> result = new ArrayList<>();
        String query = "SELECT * FROM bucket";
        try (PreparedStatement preparedStatement = JavaBeanFactory.getConnection().prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery(query);
            while (resultSet.next()) {
                Bucket loaded = new Bucket();
                loaded.setId(resultSet.getLong("id"));
                loaded.setSharedFile(resultSet.getBlob("shared_file"));
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
        try (PreparedStatement preparedStatement = JavaBeanFactory.getConnection().prepareStatement(query)) {
            ResultSet resultSet;
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery(query);
            bucket.setId(resultSet.getLong("id"));
            bucket.setSharedFile(resultSet.getBlob("shared_file"));
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
