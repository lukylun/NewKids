import styled from 'styled-components';

export const QuizResultWrapper = styled.div`
	min-height: calc(100vh);

	.score-wrapper {
		margin-top: 3rem;
		display: flex;
		justify-content: center;
		gap: 2rem;
	}

	.score {
		color: var(--sub-color-1);
		font-size: 5rem;
	}

	.sub-title-wrapper {
		margin-top: 3rem;
		display: flex;
		justify-content: center;
		align-item: center;
	}

	.sub-title {
		font-size: 2rem;
	}

	.answer-wrapper {
		margin-top: 3rem;
		display: flex;
		justify-content: center;
		align-item: center;
	}

	.answer {
		font-size: 5rem;
		color: var(--sub-color-1);
	}

	.exp-wrapper {
		margin-top: 3rem;
		display: flex;
		justify-content: center;
		align-item: center;
	}

	.quiz-button {
		min-height: 160px;
		display: flex;
		justify-content: center;
		align-item: center;
		margin-top: 3rem;
	}
`;
