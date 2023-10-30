import styled from 'styled-components';

export const WordListItemWrapper = styled.div`
	.word-list-item-wrapper {
		display: flex;
		flex-direction: column;
		gap: 10px;
		.word-item-description {
			width: 70%;
			text-align: center;
			color: var(--gray-500);
			font-size: 16px;
			margin-bottom: 30px;
		}
		.word-list-item {
			height: 100%;
			display: flex;
			justify-content: space-evenly;

			> p {
				flex: 1;
				max-width: 100px;
				white-space: nowrap;
				overflow: hidden;
				text-overflow: ellipsis;
				font-size: 25px;
			}
		}
		.item-vocabulary-content:hover {
			cursor: pointer;
		}

		.overlay {
			position: fixed;
			top: 50%;
			left: 50%;
			transform: translate(-50%, -50%);
			background-color: #fff;
			border: 1px solid #ccc;
			padding: 20px;
			z-index: 9999;
			height: 250px;
			width: 450px;
			border-radius: var(--radius-m);
		}

		.overlay-content {
			display: flex;
			flex-direction: column;
			align-items: center;
			gap: 30px;
			.selected-item-content {
				width: 100%;
				text-align: center;
				font-size: 20px;
			}
			.selected-item-description {
				font-size: 20px;
				color: var(--gray-500);
				margin-left: 15px;
				max-height: 90px;
				overflow-y: auto;
			}
			.overlay-header {
				width: 100%;
				display: flex;
				svg {
					fill: var(--danger-color);
				}
			}
			.word-item-description {
				flex: 1;
				max-width: 10%;
				white-space: nowrap;
				overflow: hidden;
				text-overflow: ellipsis;
			}
		}
		input {
			width: 25px;
			height: 25px;
		}
	}
`;
