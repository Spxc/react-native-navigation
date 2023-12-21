import React, { Component } from 'react';
import { ComponentProps } from '../ComponentProps';
import { LayoutComponent } from './LayoutComponent';
import ParentNode from '../Layouts/ParentNode';
import { observer } from 'remx';

export const Stack = observer(
  class extends Component<ComponentProps> {
    render() {
      const children = this.props.layoutNode.children;
      return children.map((child: ParentNode, i: number) => {
        return <LayoutComponent key={child.nodeId} layoutNode={child} backButton={i > 0} />;
      });
    }
  }
);
