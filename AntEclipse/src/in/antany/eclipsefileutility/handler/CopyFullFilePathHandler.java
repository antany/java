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

public class CopyFullFilePathHandler implements IHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {

	}

	@Override
	public void dispose() {

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IEditorPart editorPart = HandlerUtil.getActiveEditor(event);

		if (editorPart != null) {

			IEditorInput iEditorInput = editorPart.getEditorInput();
			if (iEditorInput != null) {

				IFileEditorInput ife = (IFileEditorInput) iEditorInput;
				if (ife != null) {
					CommonUtils.copyStringToClipBoard(ife.getFile()
							.getLocation().toOSString());
				}
			}
		}
		return null;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isHandled() {

		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {

	}

	/*
	 * private String getFileFullPath(ExecutionEvent event) { IWorkbenchWindow
	 * win = HandlerUtil.getActiveWorkbenchWindow(event); IWorkbenchPage page =
	 * win.getActivePage(); IEditorPart editorPart = page.getActiveEditor();
	 * IEditorInput editorInput = editorPart.getEditorInput(); IFileEditorInput
	 * ife = (IFileEditorInput) editorInput;
	 * 
	 * IWorkbenchWindow win = HandlerUtil.getActiveWorkbenchWindow(event);
	 * IWorkbenchPage page = win.getActivePage(); IEditorPart editorPart =
	 * page.getActiveEditor();
	 * 
	 * return ife.getFile().getLocation().toOSString(); }
	 */

}
