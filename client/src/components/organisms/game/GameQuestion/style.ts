import styled from 'styled-components';

export const GameQuestionContainer = styled.div`
	margin-top: 3rem;
	min-height: calc(100vh);

	.question-box {
		width: 800px;
		height: 600px;
		padding: 20px;
		box-shadow: 0 0 0 5px rgba(1, 1, 0, 0.5);
		border-radius: 25px;
		display: block;
		margin-left: auto;
		margin-right: auto;
		margin-top: 3rem;
	}

	.article-button-wrapper {
		margin-top: 3rem;
		min-height: 160px;
		display: flex;
		justify-content: center;
		align-item: center;
	}

	.meaning {
		margin-top: 3rem;
		text-align: left;
		font-size: 2rem;
	}

	.question-wrapper {
		display: flex;
		justify-content: center;
	}

	.question {
		margin-bottom: 50px;
	}

	.hr {
		margin-bottom: 1.5rem;
	}

	.input-wrapper {
		display: flex;
		justify-content: center;
	}

	.button-wrapper {
		margin-top: 5px;
		margin-left: 50px;
	}
`;
