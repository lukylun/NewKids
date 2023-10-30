import styled from 'styled-components';

export const MyVocabularyContainer = styled.div`
	.notebook-size {
		margin-top: 3rem;
		width: 100%;
		height: 700px;
		border-radius: 50px;
		border: 3px solid var(--gray-500);
		position: relative;
		.voca-book {
			margin-top: 7rem;
			width: 100%;
			height: 50%;
			display: flex;
			.left-page,
			.right-page {
				height: 100%;
				width: 50%;
				font-size: 20px;
				font-weight: bold;
				.left-title,
				.right-title {
					display: flex;
					justify-content: space-evenly;
					> p {
						font-size: 20px;
					}
				}
				> hr {
					margin-top: 1rem;
					width: 90%;
					border: 1px solid var(--gray-200);
				}
			}
		}
	}

	.ring {
		position: absolute;
		top: -20px;
		left: 50%;
		transform: translateX(-50%) rotate(10deg);
		width: 50px;
		height: 50px;
		background-color: rgba(255, 255, 255, 0);
		border: 5.5px solid var(--gray-200);
		border-radius: 50%;
		border-bottom: 2.5px solid transparent;
		border-left: 2.5px solid transparent;
		z-index: 1;
	}

	.ring-border {
		position: absolute;
		top: -20px;
		left: 50%;
		transform: translateX(-50%) rotate(10deg);
		width: 50px;
		height: 50px;
		background-color: rgba(255, 255, 255, 0);
		border: 8px solid var(--gray-500);
		border-radius: 50%;
		border-bottom: 2.5px solid transparent;
		border-left: 2.5px solid transparent;
	}

	.ring-border-1 {
		position: absolute;
		top: -22px;
		left: 45%;
		transform: translateX(-45%) rotate(10deg);
		width: 50px;
		height: 50px;
		background-color: rgba(255, 255, 255, 0);
		border: 8px solid var(--gray-500);
		border-radius: 65%;
		border-bottom: 2.5px solid transparent;
		border-left: 2.5px solid transparent;
	}

	.small-ring {
		position: absolute;
		width: 15px;
		height: 15px;
		background-color: rgba(255, 255, 255, 0.5);
		border: 2.5px solid var(--gray-500);
		border-radius: 50%;
		bottom: -3px;
		left: 60%;
		z-index: 1;
		transform: translateX(0%);
	}

	.vertical-line {
		position: absolute;
		top: 10%;
		left: 50%;
		transform: translateX(-50%);
		width: 1px;
		height: 80%;
		border-left: 1px dashed var(--gray-300);
		border-right: none;
	}
	.word-list {
		margin-top: 3rem;
		height: 100%;
		display: flex;
		flex-direction: column;
		gap: 65px;
	}
`;
