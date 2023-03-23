ALTER TABLE attribute_content
    ALTER COLUMN attribute_content TYPE JSONB
        USING attribute_content::JSONB;

CREATE TABLE attribute_content_item
(
    uuid                   uuid not null,
    attribute_content_uuid uuid not null,
    json                   JSONB,
    PRIMARY KEY (uuid)
);
