package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import play.libs.Json;
import play.twirl.api.Html;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class JSONUtils {

    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static ArrayNode newArrayNode() {
        return objectMapper.createArrayNode();
    }

    /**
     * Create a new ArrayNode from the given ObjectNodes
     * @param values
     * @return
     */
    public static ArrayNode newArrayNode(Collection<ObjectNode> values) {
        ArrayNode result = newArrayNode();
        for (ObjectNode value : values) {
            result.add(value);
        }
        return result;
    }

    /**
     * Create a JSON array as string from the given ObjectNodes
     * @param values
     * @return
     */
    public static String toJson(Collection<ObjectNode> values) {
        return Json.stringify(newArrayNode(values));
    }

    /**
     * Create a template-friendly Html which contains a JSON array from the given ObjectNodes
     * @param values
     * @return
     */
    public static Html toJsonHtml(Collection<ObjectNode> values) {
        return Html.apply(toJson(values));
    }

    /**
     * Same as the method above but it accepts Scala Seqs
     * @param values
     * @return
     */
    public static Html toJsonHtml(scala.collection.Seq<ObjectNode> values) {
        return Html.apply(toJson(scala.collection.JavaConversions.asJavaCollection(values)));
    }


    private static final TypeReference<Map<String, Object>> stringObjectMapTypeRef = new TypeReference<Map<String, Object>>() {};
    /**
     * Create a map from json object
     * @param json
     * @return
     */
    public static Map<String, Object> toMap(String json) {
        if(StringUtils.isEmpty(json) ) {
            return null;
        } else {
            try {
                return objectMapper.readValue(json, stringObjectMapTypeRef);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static <T> void update(T entity, JsonNode jsonNode) {
        try {
            objectMapper.readerForUpdating(entity).readValue(jsonNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String textOrNull(JsonNode jsonNode) {
        return jsonNode == null ? null : (jsonNode.isNull() ? null : jsonNode.asText());
    }

    public static Long longOrNull(JsonNode jsonNode) {
        return jsonNode == null ? null : (jsonNode.isNull() ? null : jsonNode.asLong());
    }

}