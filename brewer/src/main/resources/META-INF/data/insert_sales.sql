 insert into sale (creation_date, total_value, status, code_client, code_user) 
  values (
    FROM_UNIXTIME(ROUND((RAND() * (1473292800 - 1454284800) + 1454284800)))
  , round(rand() * 10000, 2)
  , 'ISSUED'
  , round(rand() * 7) + 1
  , round(rand() * 2) + 1)