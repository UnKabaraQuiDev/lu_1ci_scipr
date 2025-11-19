import java.awt.Graphics2D;
import java.awt.Point;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = Rectangle.class, name = "rectangle"), @JsonSubTypes.Type(value = Line.class, name = "line") })
public interface CustomShape {

	void setStartpoint(Point p1);

	void setEndpoint(Point p2);

	void draw(Graphics2D g2d);

}
