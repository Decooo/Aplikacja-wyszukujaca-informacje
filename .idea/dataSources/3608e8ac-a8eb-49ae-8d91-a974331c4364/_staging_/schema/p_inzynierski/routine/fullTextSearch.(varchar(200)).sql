CREATE PROCEDURE fullTextSearch(IN zapytanie VARCHAR(200))
  BEGIN
SELECT * FROM ogloszenie WHERE MATCH (tytul,lokalizacja,opis) AGAINST(zapytanie WITH QUERY EXPANSION) LIMIT 15;
END;
