SELECT *
FROM users u
WHERE u.username = ?;


SELECT
  u.*,
  r.title
FROM users u
  JOIN role r ON u.role_id = r.role_id
WHERE u.username = ?;

INSERT INTO users (first_name, second_name,
                  username, password, email, role_id) VALUES (?, ?, ?, ?, ?, ?);