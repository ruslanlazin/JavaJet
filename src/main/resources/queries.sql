SELECT *
FROM users u
WHERE u.username = ?;


SELECT
  u.*,
  r.title
FROM users u
  JOIN role r ON u.role_id = r.role_id
WHERE u.username = ?;