import styled from 'styled-components';

export const GameResultWrapper = styled.div`
	min-height: calc(100vh);

	.score-wrapper {
		margin-top: 3rem;
		display: flex;
		gap: 1rem;
		justify-content: center;
	}

	.score {
		color: var(--main-color);
		font-size: 5rem;
	}

	.sub-title-wrapper {
		margin-top: 3rem;
		display: flex;
		justify-content: center;
	}

	.sub-title {
		font-size: 2rem;
	}

	.answer-wrapper {
		margin-top: 3rem;
		display: flex;
		justify-content: center;
	}

	.answer {
		font-size: 5rem;
		color: var(--main-color);
	}

	.exp-wrapper {
		margin-top: 3rem;
		display: flex;
		justify-content: center;
	}

	.quiz-button {
		min-height: 160px;
		display: flex;
		justify-content: center;
		margin-top: 3rem;
	}
`;
