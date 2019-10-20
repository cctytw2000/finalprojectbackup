USE [finalproject]
GO

/****** Object:  Table [dbo].[News]    Script Date: 2019/10/16 ¤U¤È 07:02:04 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[News](
	[NewsID] [int] IDENTITY(1,1) NOT NULL,
	[NewsTypeName] [nvarchar](20) NOT NULL,
	[Title] [nvarchar](50) NOT NULL,
	[PublishedDate] [timestamp] NOT NULL,
	[Publisher] [nvarchar](50) NOT NULL,
	[Article] [nvarchar](max) NOT NULL,
	[Like] [int] NULL,
	[Clicks] [int] NULL,
	[IsVisable] [bit] NULL,
 CONSTRAINT [PK_News] PRIMARY KEY CLUSTERED 
(
	[NewsID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

ALTER TABLE [dbo].[News]  WITH CHECK ADD  CONSTRAINT [FK_News_NewsType] FOREIGN KEY([NewsTypeName])
REFERENCES [dbo].[NewsType] ([NewsTypeName])
GO

ALTER TABLE [dbo].[News] CHECK CONSTRAINT [FK_News_NewsType]
GO


