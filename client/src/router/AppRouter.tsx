import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { RecoilRoot } from 'recoil';
import { GlobalFonts } from 'styles/GlobalFonts';
import { GlobalStyles } from 'styles/GlobalStyles';
import ApplicationLayout from 'layouts/common/ApplicationLayout';
import { GlobalKeyFrames } from 'styles/GlobalKeyFrames';
import Navigation from 'components/organisms/common/Navigation';
import IndexPage from 'pages/IndexPage';
import QuizPage from 'pages/QuizPage';
import GamePage from 'pages/GamePage';
import MyPage from 'pages/MyPage';
import LoginPage from 'pages/auth/LoginPage';
import JoinPage from 'pages/auth/JoinPage';
import MyActivityPage from 'pages/MyActivityPage';
import ArticleDetailPage from 'pages/article/ArticleDetailPage';
import ArticleFindPage from 'pages/article/ArticleFindPage';
import ScrollToTop from 'components/atoms/common/ScrollToTop';
import VocabularyPage from 'pages/VocabularyPage';
import FloatingElements from 'components/organisms/common/FloatingElements';
import PrivateRoute from './PrivateRoute';
import AuthProvider from './AuthProvider';

function AppRouter() {
	return (
		<RecoilRoot>
			<GlobalFonts />
			<GlobalStyles />
			<GlobalKeyFrames />
			<FloatingElements />
			<ApplicationLayout>
				<AuthProvider>
					<BrowserRouter>
						<ScrollToTop />
						<Navigation />
						<Routes>
							<Route path="/" element={<IndexPage />} />
							<Route path="/article" element={<ArticleFindPage />} />
							<Route path="/article/:articleId" element={<ArticleDetailPage />} />
							<Route path="/auth/login" element={<LoginPage />} />
							<Route path="/auth/join" element={<JoinPage />} />

							<Route path="/" element={<PrivateRoute />}>
								<Route path="/mypage/info" element={<MyPage />} />
								<Route path="/mypage/activity" element={<MyActivityPage />} />
								<Route path="/vocabulary" element={<VocabularyPage />} />
								<Route path="/game" element={<GamePage />} />
								<Route path="/quiz" element={<QuizPage />} />
							</Route>
						</Routes>
					</BrowserRouter>
				</AuthProvider>
			</ApplicationLayout>
		</RecoilRoot>
	);
}

export default AppRouter;
