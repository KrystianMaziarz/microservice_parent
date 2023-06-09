
CREATE TABLE pastries (
                          id         bigserial PRIMARY KEY,
                          name VARCHAR(255) NOT NULL
);

CREATE TABLE ingredients (
                             id         bigserial PRIMARY KEY,
                             name VARCHAR(255) NOT NULL,
                             quantity INT NOT NULL
);

CREATE TABLE pastry_ingredients (
                                    pastry_id BIGINT,
                                    ingredient_id BIGINT,
                                    PRIMARY KEY (pastry_id, ingredient_id),
                                    FOREIGN KEY (pastry_id) REFERENCES pastries (id),
                                    FOREIGN KEY (ingredient_id) REFERENCES ingredients (id)
);