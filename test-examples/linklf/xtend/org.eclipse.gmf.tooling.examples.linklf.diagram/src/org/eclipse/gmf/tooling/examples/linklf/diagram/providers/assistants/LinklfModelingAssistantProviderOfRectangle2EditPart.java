package org.eclipse.gmf.tooling.examples.linklf.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.tooling.examples.linklf.diagram.edit.parts.Circle2EditPart;
import org.eclipse.gmf.tooling.examples.linklf.diagram.edit.parts.CircleEditPart;
import org.eclipse.gmf.tooling.examples.linklf.diagram.edit.parts.PortEditPart;
import org.eclipse.gmf.tooling.examples.linklf.diagram.edit.parts.Rectangle2EditPart;
import org.eclipse.gmf.tooling.examples.linklf.diagram.edit.parts.RectangleEditPart;
import org.eclipse.gmf.tooling.examples.linklf.diagram.edit.parts.Rhombus2EditPart;
import org.eclipse.gmf.tooling.examples.linklf.diagram.edit.parts.RhombusEditPart;
import org.eclipse.gmf.tooling.examples.linklf.diagram.providers.LinklfElementTypes;
import org.eclipse.gmf.tooling.examples.linklf.diagram.providers.LinklfModelingAssistantProvider;

/**
 * @generated
 */
public class LinklfModelingAssistantProviderOfRectangle2EditPart extends LinklfModelingAssistantProvider {

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getRelTypesOnSource(IAdaptable source) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnSource((Rectangle2EditPart) sourceEditPart);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetRelTypesOnSource(Rectangle2EditPart source) {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(LinklfElementTypes.Link_4001);
		types.add(LinklfElementTypes.LabeledLink_4002);
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getRelTypesOnSourceAndTarget(IAdaptable source, IAdaptable target) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnSourceAndTarget((Rectangle2EditPart) sourceEditPart, targetEditPart);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetRelTypesOnSourceAndTarget(Rectangle2EditPart source, IGraphicalEditPart targetEditPart) {
		List<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof CircleEditPart) {
			types.add(LinklfElementTypes.Link_4001);
		}
		if (targetEditPart instanceof RectangleEditPart) {
			types.add(LinklfElementTypes.Link_4001);
		}
		if (targetEditPart instanceof RhombusEditPart) {
			types.add(LinklfElementTypes.Link_4001);
		}
		if (targetEditPart instanceof Circle2EditPart) {
			types.add(LinklfElementTypes.Link_4001);
		}
		if (targetEditPart instanceof Rectangle2EditPart) {
			types.add(LinklfElementTypes.Link_4001);
		}
		if (targetEditPart instanceof Rhombus2EditPart) {
			types.add(LinklfElementTypes.Link_4001);
		}
		if (targetEditPart instanceof PortEditPart) {
			types.add(LinklfElementTypes.Link_4001);
		}
		if (targetEditPart instanceof CircleEditPart) {
			types.add(LinklfElementTypes.LabeledLink_4002);
		}
		if (targetEditPart instanceof RectangleEditPart) {
			types.add(LinklfElementTypes.LabeledLink_4002);
		}
		if (targetEditPart instanceof RhombusEditPart) {
			types.add(LinklfElementTypes.LabeledLink_4002);
		}
		if (targetEditPart instanceof Circle2EditPart) {
			types.add(LinklfElementTypes.LabeledLink_4002);
		}
		if (targetEditPart instanceof Rectangle2EditPart) {
			types.add(LinklfElementTypes.LabeledLink_4002);
		}
		if (targetEditPart instanceof Rhombus2EditPart) {
			types.add(LinklfElementTypes.LabeledLink_4002);
		}
		if (targetEditPart instanceof PortEditPart) {
			types.add(LinklfElementTypes.LabeledLink_4002);
		}
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getTypesForTarget(IAdaptable source, IElementType relationshipType) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		return doGetTypesForTarget((Rectangle2EditPart) sourceEditPart, relationshipType);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetTypesForTarget(Rectangle2EditPart source, IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == LinklfElementTypes.Link_4001) {
			types.add(LinklfElementTypes.Circle_2001);
			types.add(LinklfElementTypes.Rectangle_2002);
			types.add(LinklfElementTypes.Rhombus_2004);
			types.add(LinklfElementTypes.Circle_3001);
			types.add(LinklfElementTypes.Rectangle_3003);
			types.add(LinklfElementTypes.Rhombus_3004);
			types.add(LinklfElementTypes.Port_3005);
		} else if (relationshipType == LinklfElementTypes.LabeledLink_4002) {
			types.add(LinklfElementTypes.Circle_2001);
			types.add(LinklfElementTypes.Rectangle_2002);
			types.add(LinklfElementTypes.Rhombus_2004);
			types.add(LinklfElementTypes.Circle_3001);
			types.add(LinklfElementTypes.Rectangle_3003);
			types.add(LinklfElementTypes.Rhombus_3004);
			types.add(LinklfElementTypes.Port_3005);
		}
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnTarget((Rectangle2EditPart) targetEditPart);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetRelTypesOnTarget(Rectangle2EditPart target) {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(LinklfElementTypes.Link_4001);
		types.add(LinklfElementTypes.LabeledLink_4002);
		return types;
	}

	/**
	 * @generated
	 */
	@Override
	public List<IElementType> getTypesForSource(IAdaptable target, IElementType relationshipType) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetTypesForSource((Rectangle2EditPart) targetEditPart, relationshipType);
	}

	/**
	 * @generated
	 */
	public List<IElementType> doGetTypesForSource(Rectangle2EditPart target, IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == LinklfElementTypes.Link_4001) {
			types.add(LinklfElementTypes.Circle_2001);
			types.add(LinklfElementTypes.Rectangle_2002);
			types.add(LinklfElementTypes.Rhombus_2004);
			types.add(LinklfElementTypes.Circle_3001);
			types.add(LinklfElementTypes.Rectangle_3003);
			types.add(LinklfElementTypes.Rhombus_3004);
			types.add(LinklfElementTypes.Port_3005);
		} else if (relationshipType == LinklfElementTypes.LabeledLink_4002) {
			types.add(LinklfElementTypes.Circle_2001);
			types.add(LinklfElementTypes.Rectangle_2002);
			types.add(LinklfElementTypes.Rhombus_2004);
			types.add(LinklfElementTypes.Circle_3001);
			types.add(LinklfElementTypes.Rectangle_3003);
			types.add(LinklfElementTypes.Rhombus_3004);
			types.add(LinklfElementTypes.Port_3005);
		}
		return types;
	}

}
