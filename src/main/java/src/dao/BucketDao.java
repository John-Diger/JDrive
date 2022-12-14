package src.dao;

import src.dto.ResponseDto.FindAllForm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BucketDao {

    private final ConnectionManager connectionManager;
    private final Logger logger;

    public BucketDao(ConnectionManager connectionManager, Logger logger) {
        this.connectionManager = connectionManager;
        this.logger = logger;
    }

    public List<FindAllForm> findAll() {
        List<FindAllForm> result = new ArrayList<>();
        try {
            String query = "SELECT * FROM bucket";
            PreparedStatement statement = connectionManager.makeConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                FindAllForm loaded = new FindAllForm();
                loaded.setId(resultSet.getLong("id"));
                loaded.setLoadedFile(resultSet.getBlob("shared_file"));
                loaded.setCreatedAt(resultSet.getDate("created_at"));
                loaded.setStatus(resultSet.getBoolean("status"));
                result.add(loaded);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    // DB에서 조회하기
    public void findById(long requestFileId) {
        try {
            String query = "SELECT * FROM bucket WHERE id = ?";
            ResultSet resultSet;
            try (PreparedStatement statement = connectionManager.makeConnection().prepareStatement(query)) {
                statement.setLong(1, requestFileId);
                resultSet = statement.executeQuery(query);
            }
            long id = resultSet.getLong("id");
            Blob sharedFile = resultSet.getBlob("shared_file");
            Date createdAt = resultSet.getDate("created_at");
            Boolean status = resultSet.getBoolean("status");
            System.out.println(id + " " + createdAt + status);
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}