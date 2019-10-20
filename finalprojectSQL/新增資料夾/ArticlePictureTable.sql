USE [finalproject]
GO

/****** Object:  Table [dbo].[ArticlePicture]    Script Date: 2019/10/16 ¤U¤È 07:00:54 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[ArticlePicture](
	[PictureID] [int] IDENTITY(1,1) NOT NULL,
	[NewsID] [int] NOT NULL,
	[PictureName] [nvarchar](20) NOT NULL,
	[Picture] [varbinary](max) NOT NULL,
 CONSTRAINT [PK_ArticlePicture] PRIMARY KEY CLUSTERED 
(
	[PictureID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

ALTER TABLE [dbo].[ArticlePicture]  WITH CHECK ADD  CONSTRAINT [FK_ArticlePicture_News] FOREIGN KEY([NewsID])
REFERENCES [dbo].[News] ([NewsID])
GO

ALTER TABLE [dbo].[ArticlePicture] CHECK CONSTRAINT [FK_ArticlePicture_News]
GO


