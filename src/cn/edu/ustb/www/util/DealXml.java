package cn.edu.ustb.www.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import cn.edu.ustb.www.main.LoginSystem;
import cn.edu.ustb.www.view.InsertXml;

/**
 * ��ȡһ��XML�ļ��������ȡ��������ݲ��뵽���ݿ���
 */
@SuppressWarnings("unused")
public class DealXml {
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;

	/* net���� */
	private String NET_ID = null;

	/* net���� */
	private Element netNode = null;

	public Boolean readXml(String path) {
		/* �������ݿ� */
		try {
			conn = DataBaseConnection.getConn();
			st = conn.createStatement();
		} catch (Exception sqlexception) {
			sqlexception.printStackTrace();
			System.out.println("���ݿ����ӷ����쳣��");
		}

		try {
			/* ����xml�ļ���д�����ݿ� */
			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(path);

			/* ����net�ڵ� */
			insertNet(doc);

			/* ����place�ڵ� */
			insertPlace(netNode);

			/* ����transition�ڵ� */
			insertTransition(netNode);

			/* ����arc�ڵ� */
			insertArc(netNode);
			
			return true;
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
			System.out.println("��ȡxml�ļ��쳣");
			return false;
		}

	}

	private void insertNet(Document doc) {
		String netId = null;
		String netType = null;
		/* ���net��Ϣ */
		Element root = doc.getRootElement();
		List netLists = root.getChildren("net");
		for (int i = 0; i < netLists.size(); i++) {
			netNode = (Element) netLists.get(i); // userpost�ڵ���Ԫ��
			netId = netNode.getAttributeValue("id");
			netType = netNode.getAttributeValue("type");

			try {
				String sql = "INSERT INTO net(netId, type, creater, createTime, version, isDel) VALUES('"
						+ netId
						+ "', '"
						+ netType
						+ "','"
						+ LoginSystem.user.getText().trim()
						+ "','" 
						+ df.format(new Date()) 
						+ "','" 
						+ InsertXml.version.getText().trim()
						+ "', 0)";

				st.execute(sql);

				String sql1 = "SELECT id FROM net WHERE id = (SELECT max(id) FROM net)";
				rs = st.executeQuery(sql1);
				if (rs.next()) {
					NET_ID = String.valueOf(rs.getObject(1));
				} else {
					NET_ID = "0";
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("���ݿ�����쳣");
			}
		}
	}

	private void insertPlace(Element netNode) {
		/* place String���� */
		String placeId = null;
		String positionX = null;
		String positionY = null;
		String nameValue = null;
		String nameOffsetX = null;
		String nameOffsetY = null;
		String initMarkValue = null;
		String initMarkOffsetX = null;
		String initMarkOffsetY = null;
		String capacityValue = null;

		/* place Element���� */
		Element placeNode = null;
		Element positionsNode = null;
		Element positionXYNode = null;
		Element nameNode = null;
		Element graphicNode = null;
		Element offsetXYNode = null;
		Element initMarkNode = null;
		Element capacityNode = null;
		Element initMarkGraphic = null;
		Element initMarkOffset = null;
		/* ���place��Ϣ */
		List placeLists = netNode.getChildren("place");
		for (int j = 0; j < placeLists.size(); j++) {
			placeNode = (Element) placeLists.get(j);
			placeId = placeNode.getAttributeValue("id");
			List graphicLists = placeNode.getChildren("graphics");
			for (int k = 0; k < graphicLists.size(); k++) {
				positionsNode = (Element) graphicLists.get(k);
				List position = positionsNode.getChildren("position");
				for (int l = 0; l < position.size(); l++) {
					positionXYNode = (Element) position.get(l);
					positionX = positionXYNode.getAttributeValue("x");
					positionY = positionXYNode.getAttributeValue("y");
				}
			}
			List nameLists = placeNode.getChildren("name");
			for (int m = 0; m < nameLists.size(); m++) {
				nameNode = (Element) nameLists.get(m);
				nameValue = nameNode.getChildTextTrim("value");
				List nameGraphics = nameNode.getChildren("graphics");
				for (int n = 0; n < nameGraphics.size(); n++) {
					graphicNode = (Element) nameGraphics.get(n);
					List graphicOffset = graphicNode.getChildren("offset");
					for (int o = 0; o < graphicOffset.size(); o++) {
						offsetXYNode = (Element) graphicOffset.get(o);
						nameOffsetX = offsetXYNode.getAttributeValue("x");
						nameOffsetY = offsetXYNode.getAttributeValue("y");
					}
				}
			}
			List initMarkLists = placeNode.getChildren("initialMarking");
			for (int p = 0; p < initMarkLists.size(); p++) {
				initMarkNode = (Element) initMarkLists.get(p);
				initMarkValue = initMarkNode.getChildTextTrim("value");
				List initMarkGraphics = initMarkNode.getChildren("graphics");
				for (int n = 0; n < initMarkGraphics.size(); n++) {
					initMarkGraphic = (Element) initMarkGraphics.get(n);
					List initMarkOffsets = initMarkGraphic
							.getChildren("offset");
					for (int o = 0; o < initMarkOffsets.size(); o++) {
						initMarkOffset = (Element) initMarkOffsets.get(o);
						initMarkOffsetX = initMarkOffset.getAttributeValue("x");
						initMarkOffsetY = initMarkOffset.getAttributeValue("y");
					}
				}
			}
			List capacityLists = placeNode.getChildren("capacity");
			for (int q = 0; q < capacityLists.size(); q++) {
				capacityNode = (Element) capacityLists.get(q);
				capacityValue = capacityNode.getChildTextTrim("value");
			}

			try {
				String sql = "INSERT INTO place(id, netId, positionX, positionY, nameValue, nameOffsetX, nameOffsetY, initMarkValue, initMarkOffsetX, initMarkOffsetY, capacity, isDel) VALUES('"
						+ placeId
						+ "', "
						+ NET_ID
						+ ",'"
						+ positionX
						+ "', '"
						+ positionY
						+ "','"
						+ nameValue
						+ "', '"
						+ nameOffsetX
						+ "', '"
						+ nameOffsetY
						+ "', '"
						+ initMarkValue
						+ "', '"
						+ initMarkOffsetX
						+ "', '"
						+ initMarkOffsetY
						+ "', '" + capacityValue + "', 0)";
				st.execute(sql);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("insertPlaceʱ���ݿ�����쳣");
			}
		}
	}

	private void insertTransition(Element netNode) {
		/* transition String���� */
		String positionX = null;
		String positionY = null;
		String nameValue = null;
		String nameOffsetX = null;
		String nameOffsetY = null;
		String transitionId = null;
		String orientation = null;
		String rate = null;
		String timed = null;
		String infiniteServer = null;
		String priority = null;
		String startTime = null;
		String endTime = null;

		/* transition Element���� */
		Element positionXYNode = null;
		Element nameNode = null;
		Element graphicNode = null;
		Element offsetXYNode = null;
		Element transitionNode = null;
		Element orientationNode = null;
		Element rateNode = null;
		Element timedNode = null;
		Element infiniteServerNode = null;
		Element priorityNode = null;
		Element startTimeNode = null;
		Element endTimeNode = null;

		/* ��ȡtransition��Ϣ */
		List transitionLists = netNode.getChildren("transition");
		for (int j = 0; j < transitionLists.size(); j++) {
			transitionNode = (Element) transitionLists.get(j);
			transitionId = transitionNode.getAttributeValue("id");
			List graphicLists = transitionNode.getChildren("graphics");
			for (int k = 0; k < graphicLists.size(); k++) {
				graphicNode = (Element) graphicLists.get(k);
				List positionLists = graphicNode.getChildren("position");
				for (int l = 0; l < positionLists.size(); l++) {
					positionXYNode = (Element) positionLists.get(l);
					positionX = positionXYNode.getAttributeValue("x");
					positionY = positionXYNode.getAttributeValue("y");
				}
			}
			List nameLists = transitionNode.getChildren("name");
			for (int m = 0; m < nameLists.size(); m++) {
				nameNode = (Element) nameLists.get(m);
				nameValue = nameNode.getChildTextTrim("value");
				List nameGraphics = nameNode.getChildren("graphics");
				for (int n = 0; n < nameGraphics.size(); n++) {
					graphicNode = (Element) nameGraphics.get(n);
					List offsetLists = graphicNode.getChildren("offset");
					for (int o = 0; o < offsetLists.size(); o++) {
						offsetXYNode = (Element) offsetLists.get(o);
						nameOffsetX = offsetXYNode.getAttributeValue("x");
						nameOffsetY = offsetXYNode.getAttributeValue("y");
					}
				}
			}
			List orientationLists = transitionNode.getChildren("orientation");
			for (int p = 0; p < orientationLists.size(); p++) {
				orientationNode = (Element) orientationLists.get(p);
				orientation = orientationNode.getChildTextTrim("value");
			}
			List rateLists = transitionNode.getChildren("rate");
			for (int p = 0; p < rateLists.size(); p++) {
				rateNode = (Element) rateLists.get(p);
				rate = rateNode.getChildTextTrim("value");
			}
			List timedLists = transitionNode.getChildren("timed");
			for (int p = 0; p < timedLists.size(); p++) {
				timedNode = (Element) timedLists.get(p);
				timed = timedNode.getChildTextTrim("value");
			}
			List infiniteServerLists = transitionNode
					.getChildren("infiniteServer");
			for (int p = 0; p < infiniteServerLists.size(); p++) {
				infiniteServerNode = (Element) infiniteServerLists.get(p);
				infiniteServer = infiniteServerNode.getChildTextTrim("value");
			}
			List priorityLists = transitionNode.getChildren("priority");
			for (int p = 0; p < priorityLists.size(); p++) {
				priorityNode = (Element) priorityLists.get(p);
				priority = priorityNode.getChildTextTrim("value");
			}
			List startTimeLists = transitionNode.getChildren("starttime");
			for (int p = 0; p < startTimeLists.size(); p++) {
				startTimeNode = (Element) startTimeLists.get(p);
				startTime = startTimeNode.getChildTextTrim("value");
			}
			List endTimeLists = transitionNode.getChildren("endtime");
			for (int p = 0; p < endTimeLists.size(); p++) {
				endTimeNode = (Element) endTimeLists.get(p);
				endTime = endTimeNode.getChildTextTrim("value");
			}

			try {
				String sql = "insert into transition(id, netId, positionX, positionY, nameValue, nameOffsetX, nameOffsetY, orientation, rate, timed, infiniteServer, priority, startTime, endTime, isDel) values('"
						+ transitionId
						+ "', "
						+ NET_ID
						+ ",'"
						+ positionX
						+ "', '"
						+ positionY
						+ "','"
						+ nameValue
						+ "', '"
						+ nameOffsetX
						+ "', '"
						+ nameOffsetY
						+ "', '"
						+ orientation
						+ "', '"
						+ rate
						+ "', '"
						+ timed
						+ "', '"
						+ infiniteServer
						+ "', '"
						+ priority
						+ "', '"
						+ startTime + "', '" + endTime + "', 0)";
				st.execute(sql);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("insertTransitionʱ���ݿ�����쳣��");
			}
		}
	}

	private void insertArc(Element netNode) {
		/* arc String���� */
		String arcId = null;
		String source = null;
		String target = null;
		String inscription = null;
		String tagged = null;
		String arcpathId = null;
		String arcpathX = null;
		String arcpathY = null;
		String curvePoint = null;
		String type = null;

		/* arc Element���� */
		Element arcNode = null;
		Element inscriptionNode = null;
		Element taggedNode = null;
		Element arcpathNode = null;
		Element typeNode = null;

		/* ��ȡarc��Ϣ */
		List arcLists = netNode.getChildren("arc");
		for (int j = 0; j < arcLists.size(); j++) {
			arcNode = (Element) arcLists.get(j);
			arcId = arcNode.getAttributeValue("id");
			source = arcNode.getAttributeValue("source");
			target = arcNode.getAttributeValue("target");

			List inscriptionLists = arcNode.getChildren("inscription");
			for (int p = 0; p < inscriptionLists.size(); p++) {
				inscriptionNode = (Element) inscriptionLists.get(p);
				inscription = inscriptionNode.getChildTextTrim("value");
			}
			List taggedLists = arcNode.getChildren("tagged");
			for (int p = 0; p < taggedLists.size(); p++) {
				taggedNode = (Element) taggedLists.get(p);
				tagged = taggedNode.getChildTextTrim("value");
			}
			List typeLists = arcNode.getChildren("type");
			for (int p = 0; p < typeLists.size(); p++) {
				typeNode = (Element) typeLists.get(p);
				type = typeNode.getAttributeValue("value");
			}

			try {
				String sql = "insert into arc(id, netId, source, target, inscription, tagged, isDel) values('"
						+ arcId
						+ "', "
						+ NET_ID
						+ ",'"
						+ source
						+ "', '"
						+ target + "','" + inscription + "', '" + tagged + "', 0)";
				st.execute(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}

			List arcpathLists = arcNode.getChildren("arcpath");
			for (int p = 0; p < arcpathLists.size(); p++) {
				arcpathNode = (Element) arcpathLists.get(p);
				arcpathId = arcpathNode.getAttributeValue("id");
				arcpathX = arcpathNode.getAttributeValue("x");
				arcpathY = arcpathNode.getAttributeValue("y");
				curvePoint = arcpathNode.getAttributeValue("curvePoint");

				try {
					String sql = "insert into arcpath(id, arcId, netId, x, y, curvePoint, isDel) values('"
							+ arcpathId
							+ "', '"
							+ arcId
							+ "',"
							+ NET_ID
							+ ",'"
							+ arcpathX
							+ "', '"
							+ arcpathY
							+ "','"
							+ curvePoint
							+ "', 0)";
					st.execute(sql);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("insertArcʱ���ݿ�����쳣��");
				}
			}
		}
	}
}
