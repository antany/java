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

public class CopyFileRelativePath implements IHandler {

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
				CommonUtils.copyStringToClipBoard(ife
						.getFile()
						.getLocation()
						.toOSString().replaceAll("\\\\", "/")
						.replaceAll(
								ife.getFile().getProject().getLocation()
										.toOSString().replaceAll("\\\\", "/"), ""));
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
