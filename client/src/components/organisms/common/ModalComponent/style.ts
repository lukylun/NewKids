import styled from 'styled-components';

export const ModalComponentContainer = styled.div`
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
	background-color: rgba(0, 0, 0, 0.5);
	z-index: 100;

	.modal-body {
		position: absolute;
		width: 600px;
		height: 500px;
		background-color: var(--white-color);
		border-radius: var(--radius-m);
	}

	.modal-close-btn {
		position: absolute;
		top: 15px;
		right: 15px;
		border: none;
		color: var(--danger-color);
		background-color: transparent;
		font-size: 20px;
	}

	.modal-close-btn:hover {
		cursor: pointer;
	}
`;
