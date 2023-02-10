SET AUTOCOMMIT = 0;

-- GOAL
INSERT INTO goal(goal_id, content)
VALUES (1, '일상 속 유용한 표현 배우기!');

INSERT INTO goal(goal_id, content)
VALUES (2, '다른 사람들의 피드백!');

INSERT INTO goal(goal_id, content)
VALUES (3, '듣기 능력 키우기!');

INSERT INTO goal(goal_id, content)
VALUES (4, '함께 공부할 스터디 찾기!');

INSERT INTO goal(goal_id, content)
VALUES (5, '어학 자격증 따기!');

-- SUBJECT
INSERT INTO subject(subject_id, content, image)
VALUES (1, '여행', 'https://haru-speak-s3.s3.ap-northeast-2.amazonaws.com/image/static/travle.png');

INSERT INTO subject(subject_id, content, image)
VALUES (2, '영화&음악', 'https://haru-speak-s3.s3.ap-northeast-2.amazonaws.com/image/static/movie_music.png');

INSERT INTO subject(subject_id, content, image)
VALUES (3, '일&회사', 'https://haru-speak-s3.s3.ap-northeast-2.amazonaws.com/image/static/company.png');

INSERT INTO subject(subject_id, content, image)
VALUES (4, '쇼핑', 'https://haru-speak-s3.s3.ap-northeast-2.amazonaws.com/image/static/shopping.png');

INSERT INTO subject(subject_id, content, image)
VALUES (5, '음식', 'https://haru-speak-s3.s3.ap-northeast-2.amazonaws.com/image/static/food.png');

INSERT INTO subject(subject_id, content, image)
VALUES (6, '가족&친구', 'https://haru-speak-s3.s3.ap-northeast-2.amazonaws.com/image/static/family.png');

INSERT INTO subject(subject_id, content, image)
VALUES (7, '운동&건강', 'https://haru-speak-s3.s3.ap-northeast-2.amazonaws.com/image/static/workout.png');

INSERT INTO subject(subject_id, content, image)
VALUES (8, '동네', 'https://haru-speak-s3.s3.ap-northeast-2.amazonaws.com/image/static/town.png');

INSERT INTO subject(subject_id, content, image)
VALUES (9, '연애', 'https://haru-speak-s3.s3.ap-northeast-2.amazonaws.com/image/static/love.png');

SET AUTOCOMMIT = 1;

