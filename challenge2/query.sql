SELECT DISTINCT e.event_type,
    IF((SELECT COUNT(*) FROM events e2 WHERE e2.event_type = e.event_type) = 2, 0,
        (SELECT e2.value
         FROM events e2
         WHERE e2.event_type = e.event_type
         ORDER BY e2.time DESC
         LIMIT 1 OFFSET 1) -
        (SELECT e3.value
         FROM events e3
         WHERE e3.event_type = e.event_type
         LIMIT 1)
    ) AS value
FROM events e
WHERE e.event_type IN (SELECT event_type
                       FROM events
                       GROUP BY event_type
                       HAVING COUNT(*) > 1)
ORDER BY e.event_type ASC;