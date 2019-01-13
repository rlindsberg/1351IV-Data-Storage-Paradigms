1.
SELECT guide_language.name FROM (guide_language
JOIN guide ON guide.social_sec_no = guide_language.social_sec_no)
WHERE (language = 'English' OR language = 'German')
GROUP BY name HAVING count(*) > 1;

2.
SELECT DISTINCT exhibition.* FROM ((exhibition
LEFT JOIN artwork_exhibition ON artwork_exhibition.exhibition_id = exhibition.id)
LEFT JOIN artwork ON artwork.id = artwork_exhibition.artwork_id)
LEFT JOIN artist ON artist.id = artwork.artist_id
WHERE artist.Artist_name = 'Salvador Dalí'

3.
-- SELECT artwork.name, artist.name, COUNT(*) FROM (product_artwork
-- JOIN artwork ON artwork.id = product_artwork.artwork_id)
-- JOIN artist ON artist.id = artwork.artist_id
-- GROUP BY artwork_id;

SELECT artwork.name, artist.name, COUNT(*) AS antal FROM (product_artwork
LEFT JOIN artwork ON artwork.id = product_artwork.artwork_id)
LEFT JOIN artist ON artist.id = artwork.artist_id
GROUP BY artwork.name, artist.name

4.
-- SELECT artwork.name, artwork.description FROM ((product_artwork
-- LEFT JOIN artwork ON artwork.id = product_artwork.artwork_id)
-- LEFT JOIN artist ON artist.id = artwork.artist_id)
-- LEFT JOIN product ON product.barcode = product_artwork.product_barcode
-- WHERE product.type != 'T-shirt'
-- GROUP BY artwork_id;

SELECT artwork.name, artwork.description FROM ((product_artwork
LEFT JOIN artwork ON artwork.id = product_artwork.artwork_id)
LEFT JOIN artist ON artist.id = artwork.artist_id)
LEFT JOIN product ON product.barcode = product_artwork.product_barcode
WHERE product.type <> 'T-shirt'
GROUP BY product_artwork.artwork_id, artwork.name, artwork.description

5.
SELECT exhibition.name, guide.social_sec_no, guide.name FROM (exhibition_guide
LEFT JOIN exhibition ON exhibition_guide.exhibition_id = exhibition.id)
LEFT JOIN guide ON guide.social_sec_no = exhibition_guide.social_sec_no
WHERE exhibition.name = 'New year'

6.
SELECT e.name as exhibition_name, COUNT(*) as times_as_guide FROM (guided_tour as gt
LEFT JOIN exhibition as e on e.id = gt.exhibition_id)
WHERE (gt.guide_social_sec_no = '123456-7890' AND gt.end < NOW())
GROUP BY exhibition_id, e.name;
