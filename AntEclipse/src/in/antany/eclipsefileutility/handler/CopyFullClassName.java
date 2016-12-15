package in.antany.eclipsefileutility.handler;

import in.antany.eclipsefileutility.utils.CommonUtils;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.texteditor.AbstractTextEditor;

public class CopyFullClassName implements IHandler {

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
			String fileName = iEditorInput.getName();
			if (fileName.matches(".*\\.java")) {
				String sourceString = null;
				fileName = fileName.replaceAll("\\.java", "");
				IWorkbenchWindow win = HandlerUtil
						.getActiveWorkbenchWindow(event);

				if (win != null) {
					IWorkbenchPage page = win.getActivePage();
					if (page != null) {
						if (editorPart != null) {
							AbstractTextEditor part = (AbstractTextEditor) editorPart
									.getAdapter(AbstractTextEditor.class);
							if (part != null) {
								IDocument document = part.getDocumentProvider()
										.getDocument(part.getEditorInput());
								if (document != null) {
									sourceString = CommonUtils
											.getQualifiedClassName(document
													.get());

									if ("".equals(sourceString)) {
										sourceString = fileName;
									} else {
										sourceString = sourceString + "."
												+ fileName;
									}

								}

							}
						}
					}
					System.gc();
				}

				if (sourceString != null) {
					CommonUtils.copyStringToClipBoard(sourceString);
				} else {
					return null;
				}
			} else {
				MessageDialog.openWarning(HandlerUtil.getActiveShell(event),
						"Ant's file utils",
						"Triggered operation not allowed for non Java files.");
				return null;
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
