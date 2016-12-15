package in.antany.eclipsefileutility.handler;

import in.antany.eclipsefileutility.utils.CommonUtils;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.handlers.HandlerUtil;

public class CopyFolderName implements IHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		IEditorPart editorPart = HandlerUtil.getActiveEditor(event);
		if (editorPart != null) {
			IEditorInput iEditorInput = editorPart.getEditorInput();
			IFileEditorInput ife = (IFileEditorInput) iEditorInput;
			if (ife != null) {
				String path = "";
				path = ife.getFile().getLocation().toOSString();
				CommonUtils.copyStringToClipBoard(path.substring(0,
						(path.lastIndexOf("/") == -1 ? path.lastIndexOf("\\")
								: path.lastIndexOf("/")) + 1));
			}
		}
		return null;

	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isHandled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

}
