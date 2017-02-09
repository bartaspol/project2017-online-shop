<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="modal fade" id="contact" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<form class="form-horizontal" role="form">
				<div class="modal-header">
					<h4>
						Kontakt
						</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="contact-name" class="col-sm-2 control-label">Imię
							i nazwisko</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="contact-name"
								placeholder="Imie i nazwisko">
						</div>
					</div>
					<div class="form-group">
						<label for="contact-email" class="col-sm-2 control-label">E-mail</label>
						<div class="col-sm-10">
							<input type="email" class="form-control" id="contact-email"
								placeholder="przykład@domain.com">
						</div>
					</div>
					<div class="form-group">
						<label for="contact-message" class="col-sm-2 control-label">Wiadomość</label>
						<div class="col-sm-10">
							<textarea class="form-control" rows="4"
								placeholder="treść wiadomości" id="contact-message"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<a class="btn btn-default" data-dismiss="modal">Zamknij</a>
					<button type="submit" class="btn btn-primary">Wyślij</button>
				</div>
			</form>
		</div>
	</div>
</div>
