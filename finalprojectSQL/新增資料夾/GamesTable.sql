USE [finalproject]
GO

/****** Object:  Table [dbo].[Games]    Script Date: 2019/10/16 ¤U¤È 07:01:14 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Games](
	[GameID] [int] NOT NULL,
	[GameTypeName] [nvarchar](20) NOT NULL,
	[GameName] [nvarchar](50) NOT NULL,
	[PublishedDate] [date] NULL,
	[Publisher] [nvarchar](50) NOT NULL,
	[Platform] [nvarchar](20) NOT NULL,
	[NewsTypeID] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_Games] PRIMARY KEY CLUSTERED 
(
	[GameID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Games]  WITH CHECK ADD  CONSTRAINT [FK_Games_GameType] FOREIGN KEY([GameTypeName])
REFERENCES [dbo].[GameType] ([GameTypeName])
GO

ALTER TABLE [dbo].[Games] CHECK CONSTRAINT [FK_Games_GameType]
GO

ALTER TABLE [dbo].[Games]  WITH CHECK ADD  CONSTRAINT [FK_Games_NewsType] FOREIGN KEY([NewsTypeID])
REFERENCES [dbo].[NewsType] ([NewsTypeID])
GO

ALTER TABLE [dbo].[Games] CHECK CONSTRAINT [FK_Games_NewsType]
GO


